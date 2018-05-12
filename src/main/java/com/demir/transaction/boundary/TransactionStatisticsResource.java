package com.demir.transaction.boundary;

import com.demir.transaction.entity.TransactionStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User: muratdemir
 * Date: 12.05.2018
 * Time: 23:58
 */

@CrossOrigin
@RestController
@RequestMapping("statistics")
public class TransactionStatisticsResource {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TransactionStatistics get() {
        return new TransactionStatistics();
    }


}
