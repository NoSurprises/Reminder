package nick.reminder.dto;

/**
 * Created by Nick on 1/10/2018.
 */

public class RemindDTO {
    private String title;

    private String description = "test";
    private String tab;

    public RemindDTO(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getTitle() {
        return title;
    }
}
