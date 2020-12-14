package team13.pulsbes.dtos;

public class LectureIdStudentId {
    private String studentId;
    private Integer lectureId;

    public LectureIdStudentId(String studentId,Integer lectureId) {
        this.studentId = studentId;
        this.lectureId = lectureId;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
