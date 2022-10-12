package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.models.Freelancer;
import jact.lagaltproject.models.Project;
import jact.lagaltproject.models.dtos.project.ProjectDTO;
import jact.lagaltproject.services.project.ProjectService;
import jact.lagaltproject.util.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    /*
     *  Abase URL is defined and the relevant service is injected.
     */

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class))) })
    })
    @GetMapping // GET: localhost:8080/api/v1/projects
    public ResponseEntity<Collection<Project>> getAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @Operation(summary = "Gets a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Projects with supplied ID does not exist",
                    content = @Content)
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/projects/1
    public ResponseEntity<Project> findById(@PathVariable long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @Operation(summary = "Searches after projects by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Project.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Projects with supplied ID does not exist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/projects/search?name=aProjectName
    public ResponseEntity<Collection<Project>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(projectService.findAllByName(name));
    }

    @Operation(summary = "Searches for projects by field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Success",
            content = { @Content(mediaType = "apllication/json",
            schema = @Schema(implementation = Project.class))}),
            @ApiResponse(responseCode = "404",
            description = "No projects where found with given field",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search") // GET: localhost:8080/ap/v1/projects/search?field=aProjectName
    public ResponseEntity<Collection<Project>> findByField(@RequestParam String field){
        return ResponseEntity.ok(projectService.findAllByField(field));
    }

    @Operation(summary = "Adds a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Project successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/projects
    public ResponseEntity add(@RequestBody Project project) {
        Project aProject = projectService.add(project);
        URI location = URI.create("projects/" + aProject.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Project successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/projects/1
    public ResponseEntity update(@RequestBody Project aProject, @PathVariable int id) {
        // Validates if body is correct
        if(id != aProject.getId())
            return ResponseEntity.badRequest().build();
        projectService.update(aProject);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "410",
                    description = "Project successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Project with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/projects/1
    public ResponseEntity delete(@PathVariable long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
