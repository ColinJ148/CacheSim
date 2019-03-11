import java.io.FileReader;
import java.util.Scanner;

class DynamicNode {

  Object info;
  DynamicNode next;

  public DynamicNode(Object x, DynamicNode n) {
    info = x;
    next = n;
  }

  public Object getInfo() {
    return info;
  }

  public DynamicNode getNext() {
    return next;
  }

  public void setInfo(Object x) {
    info = x;
  }

  public void setNext(DynamicNode n) {
    next = n;
  }


}


class DynamicQueue {

  private DynamicNode front, rear;

  public DynamicQueue() {
    front = rear = null;
  }

  public boolean empty() {
    return (front == null);
  }

  public void insert(Object x) {
    DynamicNode p = new DynamicNode(x, null);

    if (empty()) {
      front = rear = p;
    } else {
      rear.setNext(p);
    }
    rear = p;
  }

  public Object remove() {
    if (empty()) {
      System.out.println("Queue underflow");
      System.exit(1);
    }
    DynamicNode p = front;
    Object temp = p.getInfo();
    front = p.getNext();
    if (front == null) {
      rear = null;
    }
    return temp;

  }


}


public class Main {

  public static void main(String[] args) {
    String fileString = parseFile();
    int N = 4;
    int QUEUESIZE = 4;
    int queuesizes[] = new int[N];
    DynamicQueue[] queues = new DynamicQueue[N];

    for (int i = 0; i < queues.length; i++) {
      queues[i] = new DynamicQueue();
    }

    for (int i = 0; i < fileString.length(); i = i + 2) {
      int j = Character.getNumericValue(fileString.charAt(i + 1));
      char letter = fileString.charAt(i);
      DynamicQueue tmp = queues[j];
      while (tmp != null) {
        if (queues[j].empty()) {    //if queue is empty
          queues[j].insert(letter);
          queuesizes[j] += 1;
          break;
        } else if (!searchQue(tmp, letter) && queuesizes[j] != QUEUESIZE) {
          queues[j].insert(letter);
          queuesizes[j] += 1;
          break;
        } else {
          queues[j].remove();
          queues[j].insert(letter);
          break;

        }
      }

    }


  }

  static boolean searchQue(DynamicQueue que, Object x) {
    DynamicQueue tmp = que;
    while (tmp != null) {
      if (!tmp.equals(x)) {
        return false;
      }
      tmp.remove();
    }
    return false;
  }

  static String parseFile() {
    String outString;
    try {
      Scanner in = new Scanner(new FileReader("src\\data.txt"));
      StringBuilder sb = new StringBuilder();
      while (in.hasNext()) {
        sb.append(in.next());
      }
      in.close();
      outString = sb.toString();
      return outString;
    } catch (Exception e) {
      System.out.println("fileRead Error");
    }
    return null;
  }
}
