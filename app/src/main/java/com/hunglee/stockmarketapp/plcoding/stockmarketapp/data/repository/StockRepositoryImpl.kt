package com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.repository

import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.csv.CSVParser
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.csv.CompanyListingParser
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.csv.IntradayInfoParser
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.local.StockDatabase
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.mapper.toCompanyInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.mapper.toCompanyListing
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.mapper.toCompanyListingEntity
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.remote.StockApi
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyListing
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.IntradayInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.repository.StockRepository
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.util.Resource
import com.opencsv.CSVReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListings(query)
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response  = api.getListings()
                companyListingParser.parse(response.byteStream())


            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListings("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))

            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)

        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )

        }catch (e: HttpException ) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())

        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )

        }catch (e: HttpException ) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }
    }
}