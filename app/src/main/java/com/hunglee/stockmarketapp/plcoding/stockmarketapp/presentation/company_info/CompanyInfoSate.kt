package com.hunglee.stockmarketapp.plcoding.stockmarketapp.presentation.company_info

import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.CompanyInfo
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoSate(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)