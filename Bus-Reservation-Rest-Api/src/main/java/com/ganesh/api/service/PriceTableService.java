package com.ganesh.api.service;

import com.ganesh.api.entity.PriceTable;

public interface PriceTableService {
    PriceTable updatePriceTable(PriceTable priceTable);
    PriceTable getPriceTable();
}
