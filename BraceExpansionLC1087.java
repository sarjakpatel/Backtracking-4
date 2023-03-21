// "static void main" must be defined in a public class.
class Main {


    //Brace Expansion

    //Time Complexity: O(k^(n/k)) , k = average length of each bloack, n/k = total block in s
    //Space Complexity: O(n/k)

    List<String> result;

    public String[] expansion(String s){

        result = new ArrayList<>();                                 //to store the result

        List<List<Character>> blocks = new ArrayList<>();           //to get each block "{ }" from s and store in the blocks list so it'll be easier for us to recurse from that

        int i=0;                                                    //pointer for s

        while(i<s.length()){                                        //iterate till i reaches to end of s

            char c = s.charAt(i);                                   //get each character

            List<Character> block = new ArrayList<>();              //for each character start a new block

            if(c=='{'){                                             //check if current character is '{'

                i++;                                                //if so, then increament the i

                while(s.charAt(i) != '}'){                          //iterate till we get the '}'

                    if(s.charAt(i) !=','){                          //check if current character is not ','
                        block.add(s.charAt(i));                     //if it's not ',' then add it into block list
                    }
                    i++;                                        //increament the i
                }
            }
            else{
                block.add(c);                                   //means current character is any character, then add it into the block
            }
            Collections.sort(block);                            //sort the current block as we need result in lexicographically sorted manner
            blocks.add(block);                              //add each block in blocks list
            i++;                                            //increament the i
        }

        dfs(blocks, 0, new StringBuilder());                //call the dfs function by passing blocks list, and starting index 0 and new StringBuilder

        String[] re = new String[result.size()];            //we need to return the String[] so we just conver the result list into array and return

        for(int j=0; j<result.size(); j++){
            re[j] = result.get(j);
        }
        return re;
    }

    private void dfs(List<List<Character>> blocks, int index, StringBuilder sb){

        //base
        if(index == blocks.size()){                                 //check if index reaches to blocks.size(), means we iterated over each block
            result.add(sb.toString());                              //so convert stringbuilder to string and add it to the result list and return
            return;
        }

        //logic
        List<Character> block = blocks.get(index);              //otherwise, get the block

        for(int i=0; i<block.size(); i++){                     //iterate through block

            //action
            sb.append(block.get(i));                        //get each character from the block, append it to the sb

            //recurse
            dfs(blocks, index+1, sb);                       //call the dfs on next block

            //backtrack
            sb.deleteCharAt(sb.length()-1);                 //and remove the appended character from sb (backtrack)
        }
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!");
        Main obj = new Main();
        String s = "{b,a,c}de{x,y,z}";
        String[] re = obj.expansion(s);

        for(String r: re){
            System.out.print(r  +", ");
        }
    }
}


public class BraceExpansionLC1087 {
}
