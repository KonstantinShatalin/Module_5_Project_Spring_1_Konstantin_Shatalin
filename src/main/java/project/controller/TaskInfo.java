package project.controller;

import lombok.Getter;
import project.entity.Status;

@Getter
public class TaskInfo {
    private String description;
    private Status status;
}
