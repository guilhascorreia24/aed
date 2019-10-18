import pt.ualg.fct.aed.Arrays;

class UnionFind {
    private int[] id; 
    public UnionFind( int n ){  
        id = new int[n];
        for ( int i = 0; i < n; i++ )
            id[i] = i;
    }
    public int find( int x ){
        if ( id[x] < 0 )
            return x;
    	return id[x];
    }
    public void union( int x, int y ){  
        if ( id[x] <= id[y] )
        {
            if ( id[x] == id[y] )
                id[x]--;
            id[y] = x;
        }
        else
            id[x] = y;
    }
    public String mkString()
    {
      return Arrays.mkString(id);
    }
}