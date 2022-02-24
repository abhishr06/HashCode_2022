import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Logic {

    public static void output(PriorityQueue<Project> projects, Map<String, List<Contributor>> contributors, BufferedWriter out) throws IOException {
        List<Project> remaining = new ArrayList<>();
        Map<String,Boolean> avail = new HashMap<>();
        out.write(projects.size()+"");
        out.newLine();
        while (!projects.isEmpty()){
            Project project = projects.poll();
            boolean checkValid = checkProject(project,contributors,avail,out);
            if(!checkValid)
                remaining.add(project);
            else{
                for(Project project1:remaining){
                    projects.add(project1);
                }
                remaining.clear();
            }

        }
    }

    private static boolean checkProject(Project project, Map<String, List<Contributor>> contributors, Map<String, Boolean> avail, BufferedWriter out) throws IOException {
    List<Skill> skills = project.getSkillList();
    List<String> result = isvalid(contributors,skills,avail);
    int ll=0;
    if(result.size()>0){
        out.write(project.getProjectName());
        out.newLine();
        for(Skill skill:skills) {
            if (ll < result.size()) {
                int m = ll;

                contributors.get(skill.getSkillName()).stream().forEach(t -> {
                    if (t.getName().equalsIgnoreCase(result.get(m))) {
                        for (Skill skillInc : t.getSkillSet()) {
                            if (skillInc.getSkillName().equalsIgnoreCase(skill.getSkillName())) {
                                skillInc.setLevel(skillInc.getLevel() + 1);
                            }
                        }
                    }
                });
                out.write(result.get(m)+" ");
                ll++;
            }
        }
        out.newLine();
        return true;
        }
    return false;
    }

    private static  List<String> isvalid(Map<String, List<Contributor>> contributors, List<Skill> skills, Map<String, Boolean> avail) {
       Set<String> list = new HashSet<>();
       List<Skill> mentorList = new ArrayList<>();
        int rem = 0;
        for (Skill skill : skills) {
            String skillName = skill.getSkillName();
            List<Contributor> contributorList = contributors.get(skillName);
            if(contributorList!=null) {
                for (Contributor contributor : contributorList) {
                        List<Skill> skillContributor = contributor.getSkillSet();
                        for (Skill skillCont : skillContributor) {
                            boolean isFound = false;
                            if (skillCont.getSkillName().equalsIgnoreCase(skillName)) {
                                if (skillCont.getLevel() < skill.getLevel() - 1) {
                                    return new ArrayList<>();
                                } else {
                                    list.add(contributor.getName());
                                    isFound = true;
                                }
                                if (skillCont.getLevel() == skill.getLevel() - 1) {
                                    mentorList.add(skill);
                                    list.add(contributor.getName());
                                    isFound = true;
                                }
                            }
                            if (isFound) {
                                for (Skill skillRem : mentorList) {
                                    if (skillCont.getSkillName().equalsIgnoreCase(skillRem.getSkillName()) && skillCont.getLevel() > skillRem.getLevel()) {
                                        rem++;
                                    }
                                }
                            }
                        }
                    }
            }
            else{
                return new ArrayList<>();
            }

        }
        if (rem != mentorList.size()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }


}
