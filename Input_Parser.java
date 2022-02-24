import java.io.*;
import java.util.*;

public class Input_Parser {

    public static void main(String[] args) throws IOException {

        File file = new File("/home/abhi/Downloads/HashCode/src/f_find_great_mentors.in.txt");
        File file_1 = new File("/home/abhi/Downloads/HashCode/src/a.out.txt");

        BufferedWriter out = new BufferedWriter(new FileWriter(file_1));

        Scanner sc = new Scanner(file);
        PriorityQueue<Project> projects = new PriorityQueue<>(new Comparator<Project>() {
            @Override
            public int compare(Project t2, Project t1) {
                if(t2.getRequiredDays()>t1.getRequiredDays()){
                    return 1;
                }
                else if(t2.getRequiredDays()<t1.getRequiredDays()){
                    return -1;
                }
                else
                    return 0;
            }
        });
        Map<String,List<Contributor>> contributors = new HashMap<>();

        String input = sc.nextLine();
        String[] arr = input.split(" ");
        Integer contributorsNum = Integer.valueOf(arr[0]);
        Integer projectsNum = Integer.valueOf(arr[1]);
        for (int i = 0; i < contributorsNum; i++) {
            input = sc.nextLine();
            arr = input.split(" ");
            Contributor contributor = new Contributor();
            contributor.setName(arr[0]);
            List<Skill> skillSet = new ArrayList<>();
            contributor.setSkillSet(skillSet);
            Integer skillNum = Integer.valueOf(arr[1]);
            for (int j = 0; j < skillNum; j++) {
                input = sc.nextLine();
                arr = input.split(" ");
                Skill skill = new Skill();
                skill.setLevel(Integer.valueOf(arr[1]));
                skill.setSkillName(arr[0]);
                skillSet.add(skill);
                if(contributors.get(arr[0])!=null){
                    contributors.get(arr[0]).add(contributor);
                }
                else{
                    List<Contributor> list = new ArrayList<>();
                    list.add(contributor);
                    contributors.put(arr[0],list);
                }
            }

        }
        for(int k=0;k<projectsNum;k++) {
            input = sc.nextLine();
            arr = input.split(" ");
            Project project = new Project();
            project.setProjectName(arr[0]);
            project.setRequiredDays(Integer.valueOf(arr[1]));
            project.setScore(Integer.valueOf(arr[2]));
            project.setBestBeforeDay(Integer.valueOf(arr[3]));
            Integer skillNum = Integer.valueOf(arr[4]);
            List<Skill> skillList = new ArrayList<>();
            project.setSkillList(skillList);
            for(int p=0;p<skillNum;p++){
                input = sc.nextLine();
                arr = input.split(" ");
                Skill skill = new Skill();
                skill.setSkillName(arr[0]);
                skill.setLevel(Integer.valueOf(arr[1]));
                skillList.add(skill);
            }
            projects.add(project);
        }

      Logic.output(projects,contributors,out);
        out.close();
    }
}
