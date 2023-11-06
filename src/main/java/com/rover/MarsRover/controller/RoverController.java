package com.rover.MarsRover.controller;


import com.rover.MarsRover.DTO.request.RoverDataRequest;
import com.rover.MarsRover.DTO.response.CoordinateDataResponse;
import com.rover.MarsRover.DTO.response.RoverDataResponse;
import com.rover.MarsRover.service.IRoverService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("api/rover")
@Validated //-> necesario para la validación desde los parametros
public class RoverController {

    private IRoverService roverService;

    @PostMapping
    public ResponseEntity<RoverDataResponse> createRover(@RequestBody @Valid RoverDataRequest roverDataRequest) {
        return new ResponseEntity<>(roverService.createRover(roverDataRequest), HttpStatus.CREATED);
    }

    @PostMapping("/initialization")
    public ResponseEntity<String> initialization() {
        return ResponseEntity.ok(roverService.initialization());
    }

    @GetMapping
    public ResponseEntity<RoverDataResponse> getRover() {
        return ResponseEntity.ok(roverService.getRover());
    }


    /*
     * @Pattern(regexp = "^[FB](,[FB])*$"
     * el signo ^ valida que el inicio solo contenga la letra F o B, y que si son más de una letra sea precedida de un coma y seguido
     * de la letra F o B, el * indica que este patrón puede repetirse cero o más veces. Y el signo $ Indica el final de la cadena
     */
    @PostMapping("/move/{commands}")
    public ResponseEntity<CoordinateDataResponse> moveRover(@Pattern(regexp = "^[FB](,[FB])*$", message = "{command.invalid}")
                                                                                            @PathVariable String commands) {

        return ResponseEntity.ok(roverService.moveRover(commands));
    }

    /*
    * @Pattern(regexp="^[RL]$"
    * el signo ^ valida que el inicio solo contenga la letra R o L, y a su vez $ indica que debe finalizar con una de estas
    */
    @PostMapping("/turn/{orientation}")
    public ResponseEntity<String> turnRover(@Pattern(regexp="^[RL]$", message = "{command.invalid}")
                                                                @PathVariable String orientation) {

        return ResponseEntity.ok(roverService.turnRover(orientation));
    }
}
