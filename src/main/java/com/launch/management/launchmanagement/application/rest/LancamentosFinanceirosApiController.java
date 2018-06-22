package com.launch.management.launchmanagement.application.rest;

import com.launch.management.launchmanagement.application.service.LaunchService;
import com.launch.management.launchmanagement.domain.Launch;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Api(value = "Lançamentos Financeiros")
@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class LancamentosFinanceirosApiController {

    private final LaunchService launchService;

    @ApiOperation(value = "Creates a Launch Financial", notes = "", response = Launch.class, tags = {"Launch"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Launch Financial Created", response = Launch.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Void.class)
    })
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "Content-Type", required = true, dataType = "string", paramType = "header", defaultValue = "application/json")}
    )
    @PostMapping(value = "/launchFinancial",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<Launch> launchFinancialCreate(@ApiParam(value = "The Launch Financial", required = true) @Valid @RequestBody Launch launch) {
        Optional<Launch> launchCreated =  launchService.create(launch);
        return launchCreated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
