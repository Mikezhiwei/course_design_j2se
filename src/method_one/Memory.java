package method_one;

public class Memory {

	   /*
	    *模拟一个主存块,每个主存块的有组内序号和组间序号 
	    * 
	    * */
	    private int memory_num;//主存的标号
	    
	    private int memory_arr_num;//组内序号
	    
	    public Memory(int memory_num,int memory_arr_num)
	    {
	    	 this.memory_num=memory_num;
	         this.memory_arr_num=memory_arr_num;
	    }
	    
	    public int get_Memory_num()
	    {
	    	return (this.memory_num);
	    }
	    public int get_Memory_arr_num()
	    {
	    	 return (this.memory_arr_num);
	    }
	    
	    

}
