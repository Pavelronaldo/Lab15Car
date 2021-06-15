package nnnocturn.db.repository.impl;

import org.apache.log4j.Logger;
import nnnocturn.db.DBManager;
import nnnocturn.db.dao.BrandDAORepository;
import nnnocturn.db.entity.Brand;
import nnnocturn.db.repository.BrandRepository;
import nnnocturn.exception.DBException;

import java.util.List;

public class BrandRepositoryImpl implements BrandRepository {

    private final Logger LOGGER = Logger.getLogger(BrandRepositoryImpl.class);

    private final DBManager dbManager;

    private final BrandDAORepository brandDAORepository;

    public BrandRepositoryImpl(DBManager dbManager, BrandDAORepository brandDAORepository) {
        this.dbManager = dbManager;
        this.brandDAORepository = brandDAORepository;
    }

    /**
     * @return list brand
     */
    @Override
    public List<Brand> getBrandList() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with bills by order was get");
                return brandDAORepository.getBrandList();
            }catch (DBException e){
                LOGGER.error("Cannot get list brand " + e);
            }
            return null;
        });
    }
}
