package src;
// eager Initialization

//pros: simple, Thread safe JVM handles it
//cons: Everytime when the class is loading the instace of the the class get created even if it is not used
//public class Singleton {
//    private static final Singleton instance=new Singleton();
//    private Singleton(){}
//
//    public Singleton getInstance(){
//        return instance;
//    }
//
//}



//Lazy Initializtion

// Cons : It is not Thread safe multiple thread can create multiple object;
//public class Singleton{
//    private static Singleton instance;
//    private Singleton(){
//
//    }
//    public Singleton getInstance(){
//        if(instance==null){
//            instance= new Singleton();
//
//        }
//        return instance;
//    }
//
//}



// to make it Lazy Initialization thread safe we made syncronized method

// pros: thread safe but still slow syncronization overhead every call
//if we have 3 thread thread A lock the the method and remaining thread needs wait even though the instance already created so to avoid this we can have Double checked locking
//public class Singleton {
//    private static Singleton instance;
//    private Singleton(){
//
//    }
//    public synchronized Singleton  getInstance(){
//        if(instance==null){
//            instance= new Singleton();
//        }
//        return instance;
//
//    }
//
//}


// Why volatile : without volatile big problem can happen like the object creating is not atomic means object creation is allocate memory , assaign refernce and initiate object
//so when thread A creates object not fully initialized thread B sees instacse != null and it acceces the half initializedd object so to this we use volatile

// volatile ensure no reordering and visibility accross all the threads
// so doble checked is fine but we have the better solution that is Bill Pugh method :
//public class Singleton {
//    private static volatile Singleton instance;
//    private Singleton(){
//
//    }
//    public  Singleton  getInstance(){
//        if(instance==null){
//            synchronized (Singleton.class) {
//                if(instance==null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//
//    }
//
//}


//Bill Pugh

import java.security.PublicKey;
// why it is best "Lazy Loading , Thread Safe , No Synchronization Over Head"

// Inner class is not loaded until it needed
//class loading is handledd by Jvm
// Jvm guarentees safe Initialization
public class Singleton{
    private Singleton(){}

    private static class Helper{
        private static Singleton instance = new Singleton();
    }

    public Singleton getInstance(){
        return Helper.instance;
    }

        }
