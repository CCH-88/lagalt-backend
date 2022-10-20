package jact.lagaltproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jact.lagaltproject.models.Message;
import jact.lagaltproject.services.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/messages")
public class MessageController {


    private final MessageService messageService;

    /*
     *  Abase URL is defined and the relevant service is injected.
     */

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Adds a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Message successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Message with supplied ID not found ",
                    content = @Content)
    })
    @PostMapping // POST: localhost:8080/api/v1/messages
    public ResponseEntity add(@RequestBody Message message) {
        Message aMessage = messageService.add(message);
        URI location = URI.create("messages/" + aMessage.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Updates a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Message successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Message with supplied ID not found ",
                    content = @Content)
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/messages/1
    public ResponseEntity update(@RequestBody Message aMessage, @PathVariable int id) {
        // Validates if body is correct
        if(id != aMessage.getId())
            return ResponseEntity.badRequest().build();
        messageService.update(aMessage);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "410",
                    description = "Message successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Message with supplied ID not found ",
                    content = @Content)
    })
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/messages/1
    public ResponseEntity delete(@PathVariable long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
