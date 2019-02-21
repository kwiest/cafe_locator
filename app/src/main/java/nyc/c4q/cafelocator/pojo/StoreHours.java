package nyc.c4q.cafelocator.pojo;

/**
 * Created by jervon.arnoldd on 2/15/19.
 */

public class StoreHours {
   private String dayOfWeek;
   private String openTime;
   private String closeTime;
   private boolean isOpen;

   public void setDayOfWeek(String dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
   }

   public void setOpenTime(String openTime) {
      this.openTime = openTime;
   }

   public void setCloseTime(String closeTime) {
      this.closeTime = closeTime;
   }

   public void setOpen(boolean open) {
      isOpen = open;
   }

   public String getDayOfWeek() {
      return dayOfWeek;
   }

   public String getOpenTime() {
      return openTime;
   }

   public String getCloseTime() {
      return closeTime;
   }

   public boolean isOpen() {
      return isOpen;
   }
}