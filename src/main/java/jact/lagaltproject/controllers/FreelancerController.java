package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.mappers.FreelancerMapper;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.dtos.freelancer.FreelancerDTO;
import jact.lagaltproject.services.freelancer.FreelancerService;
import jact.lagaltproject.util.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/freelancers")
public class FreelancerController {

    Logger logger = LoggerFactory.getLogger(FreelancerController.class);
    private final FreelancerService freelancerService;
    private final FreelancerMapper freelancerMapper;

    /*
     *  Abase URL is defined and the relevant service is injected.
     */

    public FreelancerController(FreelancerService freelancerService, FreelancerMapper freelancerMapper) {
        this.freelancerService = freelancerService;
        this.freelancerMapper = freelancerMapper;
    }

    @Operation(summary = "Get all freelancers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FreelancerDTO.class)))})
    })
    @GetMapping // GET: localhost:8080/api/v1/freelancers
    public ResponseEntity<Collection<Freelancer>> getAll() {
        return ResponseEntity.ok(freelancerService.findAll());
    }

    @Operation(summary = "Gets a freelancer by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Freelancer.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID does not exist",
                    content = @Content)
    })
    @GetMapping("profile/{id}") // GET: localhost:8080/api/v1/freelancers/1
    public ResponseEntity<Freelancer> findById(@PathVariable String id) {
        if (!freelancerService.exists(id))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(freelancerService.findById(id));
    }

    @Operation(summary = "Searches after freelancers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Freelancer.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Character with supplied ID does not exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/freelancers/search?name=Thor
    public ResponseEntity<Collection<Freelancer>> findByName(@RequestParam String username) {
        return ResponseEntity.ok(freelancerService.findAllByUsername(username));
    }

    @Operation(summary = "Adds a freelancer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Freelancer successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Freelancer already Created",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/freelancers
    public ResponseEntity add(@RequestBody Map<String, String> userMap ) {
        SecurityContext sch = SecurityContextHolder.getContext();
        Authentication auth = sch.getAuthentication();
        Freelancer freelancer = new Freelancer();
        freelancer.setId(auth.getName());
        freelancer.setEmail(userMap.get("email"));
        freelancer.setUsername(userMap.get("username"));

        if(freelancerService.exists(auth.getName())) {
            return ResponseEntity.status(409).build();
        }
        freelancerService.add(freelancer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        //Freelancer aFreelancer = freelancerService.add(freelancer);
        //URI location = URI.create("freelancers/" + aFreelancer.getId());
        //return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a freelancer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Freelancer successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("profile/{id}") // PUT: localhost:8080/api/v1/freelancers/1
    public ResponseEntity update(@RequestBody FreelancerDTO freelancerDTO, @PathVariable String id) {
        SecurityContext sch = SecurityContextHolder.getContext();
        Authentication auth = sch.getAuthentication();

        // Validates if body is correct
        if (id != freelancerDTO.getId() && Objects.equals(id, auth.getName()))
            return ResponseEntity.badRequest().build();
        if (!freelancerService.exists(freelancerDTO.getId())) {
            Freelancer aFreelancer = freelancerService.add(freelancerMapper.freelancerDTOtoFreelancer(freelancerDTO));
            URI location = URI.create("freelancers/" + aFreelancer.getId());
            return ResponseEntity.created(location).build();
        }
        freelancerService.update(
                freelancerMapper.freelancerDTOtoFreelancer(freelancerDTO));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a freelancer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "410",
                    description = "Freelancer successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Freelancer with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/freelancers/1
    public ResponseEntity delete(@PathVariable String id) {
        freelancerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
