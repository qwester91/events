package ya.qwester345.classifier.category.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
public class Category {
   private UUID uuid;
   private LocalDateTime dtCreate;
   private LocalDateTime dtUpdate;
   private String description;
   private String title;

   public Category() {
   }
   @Id
   @Column
   public UUID getUuid() {
      return uuid;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }
   @Column(name = "dt_create")
   public LocalDateTime getDtCreate() {
      return dtCreate;
   }

   public void setDtCreate(LocalDateTime dtCreate) {
      this.dtCreate = dtCreate;
   }
   @Version
   @Column(name = "dt_update")
   public LocalDateTime getDtUpdate() {
      return dtUpdate;
   }

   public void setDtUpdate(LocalDateTime dtUpdate) {
      this.dtUpdate = dtUpdate;
   }
   @Column(name = "description")
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
   @Column(name = "title")
   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
