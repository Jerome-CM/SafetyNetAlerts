
package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FirestationDTO;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.service.interf.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationServiceImpl implements FirestationService {

    private static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *
     * @param firestationDTO
     * @return {@link FirestationDTO}
     */
    @Override
    public FirestationDTO saveFirestation(FirestationDTO firestationDTO){

        logger.trace("--- Call : saveFirestation ---");
        logger.info("Data send by User : {}", firestationDTO);

        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);

        try {
            firestationRepository.save(firestation);
            logger.info("--- Firestation saved --- ");
            logger.info("New : {}",firestation);
        } catch (Exception e){
            logger.error("Impossible to save a firestation : {}"+ e.getMessage());
        }
        return modelMapper.map(firestation, FirestationDTO.class);
    }

    /**
     *
     * @param firestationDTO
     * @return {@link FirestationDTO}
     */
    @Override
    public FirestationDTO updateFirestation(FirestationDTO firestationDTO){

        logger.trace("--- Call : updateFirestation ---");
        logger.info("Data send by User : {}", firestationDTO);

        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);
        List<Firestation> listStationByAddress = firestationRepository.findByAddress(firestation.getAddress());

        if(!listStationByAddress.isEmpty()){

            logger.info("Firestation finded : {}",listStationByAddress);

            firestation.setId(listStationByAddress.get(0).getId());
            logger.info("Olds infos firestation : {}", listStationByAddress.get(0));

            try {
                firestation = firestationRepository.save(firestation);
                logger.info("--- Firestation update ---");
                logger.info("News infos firestation : {}", firestation);
            } catch(Exception e){
                logger.error("Impossible to updated a firestation : {}", e.getMessage());
            }

        } else {
            logger.error("The listStationByAddress is empty");
        }

        return modelMapper.map(firestation, FirestationDTO.class);
    }

    /**
     *
     * @param firestationDTO
     * @return Reply message
     */
    @Override
    public String deleteFirestation(FirestationDTO firestationDTO){

        logger.trace("--- Call : deleteFirestation ---");
        logger.info("Data send by User : {}", firestationDTO);

        Firestation firestation = modelMapper.map(firestationDTO, Firestation.class);

        List<Firestation> listStationByNumber = firestationRepository.findByStation(firestation.getStation());
        List<Firestation> listStationByAddress = firestationRepository.findByAddress(firestation.getAddress());

        try{

            if(listStationByNumber.isEmpty()){
                logger.info("List firestation finded with the address : {}",listStationByAddress);
                firestationRepository.delete(listStationByAddress.get(0));
                logger.info("--- Firestation delete ---");
                return "Delete success";
            } else {
                logger.info("List firestation finded with the number : {}",listStationByNumber);
                firestationRepository.delete(listStationByNumber.get(0));
                logger.info("--- Firestation delete ---");
                return "Delete success";
            }

        } catch(Exception e){
            logger.error("Impossible to delete a firestation : {}", e.getMessage());
            return "Delete error";
        }
    }
}

