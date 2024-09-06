package com.sist.jobgem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "jobseekers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jobseeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jo_idx", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "us_idx", nullable = false)
    private User user;

    @Column(name = "jo_name", nullable = false, length = 10)
    private String joName;

    @Column(name = "jo_birth")
    private LocalDate joBirth;

    @Column(name = "jo_address", length = 30)
    private String joAddress;

    @Column(name = "jo_tel", nullable = false, length = 15)
    private String joTel;

    @Column(name = "jo_gender", nullable = false, length = 1)
    private String joGender;

    @Column(name = "jo_img_url", length = 100)
    private String joImgUrl;

    @Column(name = "jo_edu", length = 10)
    private String joEdu;

    @Column(name = "jo_sal", length = 10)
    private String joSal;

    @ManyToMany
    @JoinTable(name = "have_skills", joinColumns = @JoinColumn(name = "jo_idx"), inverseJoinColumns = @JoinColumn(name = "sk_idx"))
    private List<Skill> skills;

    // 필드 업데이트 메서드 추가
    public void updateFields(String joName, LocalDate joBirth, String joAddress, String joTel,
            String joGender, String joEdu, String joSal, String joImgUrl) {
        this.joName = joName;
        this.joBirth = joBirth;
        this.joAddress = joAddress;
        this.joTel = joTel;
        this.joGender = joGender;
        this.joEdu = joEdu;
        this.joSal = joSal;
        this.joImgUrl = joImgUrl;
    }
}