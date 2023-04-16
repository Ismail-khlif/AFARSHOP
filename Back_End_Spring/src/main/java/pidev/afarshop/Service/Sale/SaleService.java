package pidev.afarshop.Service.Sale;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pidev.afarshop.Repository.SaleRepository;

@Service
@AllArgsConstructor
@Slf4j
public class SaleService implements ISaleService {
    SaleRepository saleRepository;
    @Override
    public Double getTotalRevenueByProduct(Long productId) {
        return saleRepository.findTotalRevenueByProduct(productId);
    }
}
