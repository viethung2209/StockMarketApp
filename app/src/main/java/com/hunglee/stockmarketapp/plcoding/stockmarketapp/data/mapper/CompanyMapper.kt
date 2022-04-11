package com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.mapper

import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.local.CompanyListingEntity
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing() :CompanyListing {

    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity() :CompanyListingEntity {

    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo() : CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""

    )
}