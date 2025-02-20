package com.example.school.services;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@Data
public class ElementDto {

    private String name;
    private String language;
    private String version;
    private Integer startLine;
    private Integer endLine;

    public ElementDto(String language, String name, String version, Integer startLine, Integer endLine){
        this.language = language;
        this.name = name;
        this.version = version;
        this.startLine = startLine;
        this.endLine = endLine;
    }

}
