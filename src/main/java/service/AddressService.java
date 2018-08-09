package service;

import model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends ServiceBase {
    public List<Address> listAddress(){
        return addressMapper.listAddress();
    }
}
