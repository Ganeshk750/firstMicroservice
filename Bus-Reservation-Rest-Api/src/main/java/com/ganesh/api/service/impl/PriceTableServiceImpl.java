package com.ganesh.api.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.api.entity.PriceTable;
import com.ganesh.api.repository.PriceTableRepository;
import com.ganesh.api.service.PriceTableService;

@Service
@Transactional
public class PriceTableServiceImpl implements PriceTableService {
    @Autowired
    private PriceTableRepository priceTableRepository;

    @Override
    public PriceTable updatePriceTable(PriceTable priceTable) {
        priceTableRepository.deleteAll();
        priceTable.setId(null);
        return priceTableRepository.save(priceTable);
    }

    @Override
    public PriceTable getPriceTable() {
        List<PriceTable> tables = priceTableRepository.findAll();
        return tables.size() > 0 ? tables.get(0) : null;
    }
}
