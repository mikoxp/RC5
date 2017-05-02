
package rotate;

import exception.BiteOperationException;

/**
 *
 * @author moles
 */
public class BiteOperation {
     private final int biteSizeNumber = Integer.SIZE;
     /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    public int rotateLeft(int number, int n) {
        return (((number) << (n))) | ((number) >>> (biteSizeNumber - (n)));
    }

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    public int rotateRight(int number, int n) {
        return (((number) >>> (n))) | ((number) << (biteSizeNumber - (n)));
    }
    /**
     * 
     * @param sizeOfBlock size of block
     * @param data data
     * @return complement data
     * @throws exception.BiteOperationException bite exception
     */
    public byte[] complementToBlock(int sizeOfBlock,byte[] data) 
            throws BiteOperationException{
         if(data==null){
            throw new BiteOperationException("data is null");
        }
        int n=data.length;
        if(sizeOfBlock<n){
            throw new BiteOperationException("size of block is smaller than data size");
        }
        byte[] tmp=new byte[sizeOfBlock];
        sizeOfBlock--;
        n--;
        for(int i=n;i>=0;i--){
            tmp[sizeOfBlock]=data[i]; 
            sizeOfBlock--;
        }
        return tmp;
    }
}
