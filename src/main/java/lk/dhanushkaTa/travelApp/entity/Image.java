package lk.dhanushkaTa.travelApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "image")
public class Image {
    @Id
    private String id;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String fileName;
}
