package service;

import model.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService extends ServiceBase{
    public List<Video> listVideos(Integer addressId){
        return videoMapper.selectByAddress(addressId);
    }
}
