package method_two;

import java.util.ArrayList;
import java.util.List;

public class Cache {

	    private List<Memory> cache_memory_set;
	    private Memory[] memory;
	    
	   	public Cache()
	    {
		   this.memory=new Memory[2048];
		   for(int i=0;i<this.memory.length;i++)
		   {
			   this.memory[i]=new Memory(i);
		   }
		   this.cache_memory_set=new ArrayList<Memory>();
		   for(int i=0;i<16;i++)
		   {
			     int index=(int)(Math.random()*2048);
			     this.cache_memory_set.add(this.memory[index]);
		   }
	   }
	   
	   	public void find_in_cache(Memory target_memory)
	   	{
	   		  int memory_num=target_memory.get_memory_num();
	   		  int index=0;
	   		  boolean istarget=false;
	   		  for(index=0;index<this.cache_memory_set.size();index++)
	   		  {
	   			     Memory cache_memory=this.cache_memory_set.get(index);
	   			     if(memory_num==cache_memory.get_memory_num())
	   			     {
	   			    	 System.out.println("target");
	   			    	 istarget=true;
	   			    	 break;
	   			     }
	   			   
	   		  }
	   		  if(!istarget)
	   		  {
	   			     System.out.println("is  not target!!");
	   			     this.cache_memory_set.remove(0);
	   			     this.cache_memory_set.add(target_memory);
	   		  }
	   		  
	   	}
	   	
	   public static void main(String[] args) 
	   {
              Cache cache=new Cache();
              Memory memory=new Memory(18);
              cache.find_in_cache(memory);
              cache.find_in_cache(memory);
	
	   }
	  

}
