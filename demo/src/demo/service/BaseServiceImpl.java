package demo.service;

import demo.Constants;
import demo.entity.BaseEntity;
import demo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseRepository baseRepository;

    @Transactional
    @Override
    public BaseEntity save(BaseEntity o) {
        Long now = System.currentTimeMillis();
        o.setCreateTime(now);
        o.setUpdateTime(now);
        o.setState(Constants.STATE.ON.ordinal());
        baseRepository.save(o);
        return o;
    }
}
