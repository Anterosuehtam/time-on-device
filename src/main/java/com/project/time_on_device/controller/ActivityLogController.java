package com.project.time_on_device.controller;

import com.project.time_on_device.entity.ActivityLog;
import com.project.time_on_device.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @PostMapping
    public ResponseEntity<ActivityLog> createActivityLog(@RequestBody ActivityLog activityLog) {

        ActivityLog activityLogSaved = activityLogService.createActivityLog(activityLog);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityLogSaved);
    }

    @GetMapping
    public ResponseEntity<List<ActivityLog>> getAllActivities() {
        List<ActivityLog> activities = activityLogService.getAllActivities();

        return ResponseEntity.ok(activities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityLog> updateActivity(@PathVariable Integer id, @RequestBody ActivityLog activityLog) {
        ActivityLog atividadeSalva = activityLogService.updateActivityLog(id, activityLog);
        return ResponseEntity.ok(atividadeSalva);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {
        activityLogService.deleteActivityLog(id);
        return ResponseEntity.noContent().build();
    }
}
