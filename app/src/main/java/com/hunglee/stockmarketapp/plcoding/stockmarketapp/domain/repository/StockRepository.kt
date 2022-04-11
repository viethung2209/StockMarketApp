package com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.repository

import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyListing
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.IntradayInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
            fetchFromRemote: Boolean,
            query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol : String
    ) : Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ) : Resource<CompanyInfo>
}