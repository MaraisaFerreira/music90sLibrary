package com.study.mf.docs;

import com.study.mf.dto.MusicDTO;
import com.study.mf.dto.wrapper.MusicPagedModelWrapper;
import com.study.mf.exceptions.MusicExceptionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Music")
public interface MusicControllerDocs {

    @Operation(
        summary = "Get all musics from database",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicPagedModelWrapper.class))
            }),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE, schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE, schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            })
        }
    )
    ResponseEntity<PagedModel<EntityModel<MusicDTO>>> findAll(
        Integer page,
        Integer size,
        String direction,
        String orderBy
    );


    @Operation(
        summary = "Get a specif game from db using it id",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
            }),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            })
        }
    )
    ResponseEntity<MusicDTO> findById(Long id);

    @Operation(
        summary = "Add game on database",
        responses = {
            @ApiResponse(description = "Success", responseCode = "201", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            })
        }
    )
    ResponseEntity<MusicDTO> create(MusicDTO music);


    @Operation(
        summary = "Modify a game on db using it id",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicDTO.class)),
            }),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            })
        }
    )
    ResponseEntity<MusicDTO> updated(Long id, MusicDTO musicDTO);


    @Operation(
        summary = "Remove a game from db using it id",
        responses = {
            @ApiResponse(description = "No Content", responseCode = "204"),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            }),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {
                @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_YAML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
                @Content(mediaType = MediaType.APPLICATION_XML_VALUE,
                    schema = @Schema(implementation = MusicExceptionResponseDto.class)),
            })
        }
    )
    ResponseEntity<Void> delete(Long id);
}
