public class PrimeNumberGenerator {
    PrimeNode head;

    @Override
    public String toString() {
        String res = head.toString();
        PrimeNode ptr = head.next;
        while (ptr != null) {
            res += "->" + ptr.toString();
            ptr = ptr.next;
        }
        return res;
    }

    public PrimeNumberGenerator() {
        this.head = new PrimeNode(2);
    }

    public int currentPrime() {
        return head.value;
    }

    public int nextPrime() {
        if(length() == 1)
        {
            //only head in the list
            sieveOfEratosthenes();
        }
        this.head = head.next;
        return this.head.value;
    }

    public void sieveOfEratosthenes() {
        int len = this.head.value * 2 + 1;
        PrimeNode curr = this.head;

        boolean [] notPrime = new boolean[len];

        for(boolean node : notPrime)
        {
            node = false;
        }

        int jump = 2;

        while(jump < len)
        {
            int counter = jump + jump;
            while(counter < len)
            {
                notPrime[counter] = true;
                counter += jump;
            }
            jump += 1;
        }

        for(int i = this.head.value+1; i < len; i++)
        {
            if(notPrime[i] == false){
                curr.next = new PrimeNode(i);
                curr = curr.next;
            }
        }

    }

    private int length()
    {
        int length = 0;
        PrimeNode current = head;
        while(current!=null)
        {
            length++;
            current = current.next;
        }
        return length;
    }

}
