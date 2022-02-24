import java.util.List;

public class Project {

    private String projectName;

    private Integer requiredDays;

    private Integer score;

    private Integer bestBeforeDay;

    List<Skill> skillList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getRequiredDays() {
        return requiredDays;
    }

    public void setRequiredDays(Integer requiredDays) {
        this.requiredDays = requiredDays;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBestBeforeDay() {
        return bestBeforeDay;
    }

    public void setBestBeforeDay(Integer bestBeforeDay) {
        this.bestBeforeDay = bestBeforeDay;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
