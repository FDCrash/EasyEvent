package com.netcracker.converters;

import com.easyevent.converters.ArtistConverter;
import com.easyevent.dto.base.ArtistDto;
import com.easyevent.entity.ArtistEntity;
import com.netcracker.TestConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ArtistConverterTest {

    @Autowired
    private ArtistConverter artistConverter;

    private ArtistDto artistDto;
    private ArtistEntity artistEntity;
    private ArtistDto artistDtoExpected;
    private ArtistEntity artistEntityExpected;

    @Before
    public void setUp(){

    }

}
