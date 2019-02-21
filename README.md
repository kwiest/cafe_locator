# cafe_locator
Description of architecture:
For this exercise Im using MVP for its simplicity, easy to test, and separation of concern. 

The presenter handles all my background task such as connecting with the remote api and the manipulation of object. And Simply send information when requested by the View.

The View is made up of Map Activity, ListFragment and Detail fragment. Usually with MVP every view needs a presenter but I decided to forgo this in my application. Why? Because I am simply passing a List or a single object to be displayed. 

The model is being handled by RetroFit and Rxjava. I choose RetroFit because makes it relatively easy to retrieve and upload JSON via a REST based webservice. Also because its well document and tested.  Rxjava pairs good with this because all network call must be done on background  thread and rxjava handles threads in manner which is easy to read and understand. It also helps avoid multiple callbacks.

So over all my maps activity requested a store, through an interface this key is used to Query and passed back to my MapsActivity.  Thanks to rxjava he transition from background thread to UI thread is seamless. MapsActivity will then display marker on the map and last this list to ListFragment to be displayed in a recyclerview. Thanks to my Custom solution to registering click event is this recyclerview my MapsActivity is notifiled when item was clicked via its index number. The index number passed to my presents which uses it to return the correct object back to MapsActivity which it turn passes it to my Detail fragment to be displayed.  I decided to use method because i only wanted my MapsActivity to communicate with my presenter. According to MVP guidelines and present but only be accessed my one view.


Description of any trade-offs you made
There were a few trade offs made. One is communication between my views. I could have use Eventbus.Which helps with elements depending on each other to the least extent.  It would have also helped with which how we process different events occured in the application. Combine with Rxjava 2 this would have made our code clean and readable. 



Description of how to run it locally

Since i have included my api key for google maps and blue bottle coffee api does not require a key this app can be run on using the latest android studio. But internet is need on the emulator or device. 



Description of deployment and production readiness


I believe this app  is ready for deployment but there are some UI/Cosmetic changes which need to take place and a few more test need to be written. One example is creating custom map makers which would should be more in line with Blue Bottle Coffee logos/icons. Also changing the time to be the same as the Currents stores Time Zone.









