package method_one;

public class Memory {

	   /*
	    *ģ��һ�������,ÿ����������������ź������� 
	    * 
	    * */
	    private int memory_num;//����ı��
	    
	    private int memory_arr_num;//�������
	    
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
