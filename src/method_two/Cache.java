package method_two;

import java.util.ArrayList;
import java.util.List;

public class Cache {
	   
        /*
         * ȫ����ӳ�䣬������������ʵ��cache�����ݽṹ
         * */
	    private List<Memory> cache_memory_set;//cache�������ӳ���ϵ
	    
	    private Memory[] memory;
	    
	   	public Cache()
	    {
		   this.memory=new Memory[2048];
		   for(int memoryNumber=0;memoryNumber<this.memory.length;memoryNumber++)
		   {
			   this.memory[memoryNumber]=new Memory(memoryNumber);
		   }
		   this.cache_memory_set=new ArrayList<Memory>();
		   /*
		    * cache���װ��
		    * */
		   for(int index=0;index<16;index++)
		   {
			     int randomIndex=(int)(Math.random()*2048);
			     this.cache_memory_set.add(this.memory[randomIndex]);
		   }
	   }
	   
	   	public void find_in_cache(Memory target_memory)
	   	{
	   		  /*
	   		   * ȫ����ӳ��˳�����cache,����ʱ�临�Ӷ�ΪO(n);
	   		   * */
	   		  int memory_num=target_memory.get_memory_num();
	   		  int index=0;
	   		  boolean istarget=false;
	   		  for(index=0;index<this.cache_memory_set.size();index++)
	   		  {
	   			     Memory cache_memory=this.cache_memory_set.get(index);
	   			     if(memory_num==cache_memory.get_memory_num())
	   			     {
	   			    	 System.out.println("CPU is target!!");
	   			    	 istarget=true;
	   			    	 break;
	   			     }
	   			   
	   		  }
	   		  if(!istarget)
	   		  {   
	   			     /*
	   			      * δ�����ö����㷨װ��
	   			      * */
	   			     System.out.println("CPU is  not target!!");
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
