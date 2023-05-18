package com.teamz.recipe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

//    /* 해당 엔티티를 저장하기 이전에 실행 */
//    @PrePersist
//    public void onPrePersist(){
//        System.out.print(LocalDateTime.now());
//        this.createdDate = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//        this.modifiedDate = this.createdDate;
//    }
//    /* 해당 엔티티를 업데이트 하기 이전에 실행*/
//    @PreUpdate
//    public void onPreUpdate(){
//        this.modifiedDate = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//    }
}
