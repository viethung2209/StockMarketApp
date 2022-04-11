package com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.mapper

import com.hunglee.stockmarketapp.plcoding.stockmarketapp.data.remote.dto.IntradayInfoDto
import com.hunglee.stockmarketapp.plcoding.stockmarketapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayInfoDto.toIntradayInfo() : IntradayInfo {
    val parttern  = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(parttern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}