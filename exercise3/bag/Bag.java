package bag;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Bag {

   Map<Integer, Integer> map = new HashMap<Integer, Integer>();
   // elements are keys, value is # of elements

   public void add(int elt) {
      Integer value = map.get(elt);

      if (value == null) {
         map.put(elt, 1);
      } else {
         map.put(elt, value + 1);
      }
   }

   public boolean contains(int elt) {
      return map.containsKey(elt);
   }

   public int getCount(int elt) {
      if (map.containsKey(elt))
         return map.get(elt);
      else
         return 0;
   }

   public int size() {
      int size = 0;

      Set<Integer> set = map.keySet();
      Iterator<Integer> iterator = set.iterator();

      while (iterator.hasNext()) {
         size += map.get(iterator.next());
      }

      return size;
   }

   public Set<Integer> uniqueElements() {
      return map.keySet();
   }

   public void remove(int elt) {
      Integer value = map.get(elt);

      if (value == null) {
         return;
      } else if (value == 1) {
         map.remove(elt);
      } else {
         map.put(elt, value - 1);
      }
   }
}