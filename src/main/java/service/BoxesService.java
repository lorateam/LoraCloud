package service;
import model.Boxes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxesService extends ServiceBase{
    public Boxes getBox(String uuid){
        return boxesMapper.selectByUuid(uuid);
    }

    public List<Boxes> listBoxes(Integer addressId){
        return boxesMapper.selectByAddressId(addressId);
    }
}
