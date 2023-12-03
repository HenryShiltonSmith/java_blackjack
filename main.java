import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Blackjack
{
    private ArrayList<String> slUsedCards = new ArrayList<String>();
    private ArrayList<String> slValues = new ArrayList<String>();
    private String[] slSuit = {"Clubs","Diamonds","Hearts","Spades"};
    private int iPlayerValue = 0;
    private int iDealerValue = 0;

    public String GenerateCard(String sDealerorPlayer)
    {
        Random rand = new Random();
        boolean bUsed = false;
        String sCard = "";
        while(!bUsed)
        {
            // System.out.println(slValues);
            int iRandomValueValue = rand.nextInt(this.slValues.size());
            // iRandomValueValue = 9;
            String iRandomValue = String.valueOf(this.slValues.get(iRandomValueValue));
            String sRandomSuit=  String.valueOf(slSuit[rand.nextInt(4)]);
            sCard = String.valueOf(iRandomValue + " of " + sRandomSuit);
            // System.out.println(iRandomValue + " of " + sRandomSuit);

            if(!slUsedCards.contains(sCard))
            {
                slUsedCards.add(sCard);
                if(Integer.valueOf(iRandomValueValue) <= 9 && Integer.valueOf(iRandomValueValue) >= 1)
                {
                    if(sDealerorPlayer == "Player")
                    {
                        iPlayerValue = iPlayerValue + Integer.valueOf(iRandomValue);
                    }
                    else
                    {
                        iDealerValue = iDealerValue + Integer.valueOf(iRandomValue);
                    }
                }
                else if(iRandomValueValue == 0)
                {
                    if(sDealerorPlayer == "Player")
                    {
                        iPlayerValue = iPlayerValue + 11;
                    }
                    else
                    {
                        iDealerValue = iDealerValue + 11;
                    }
                }
                else
                {
                    if(sDealerorPlayer == "Player")
                    {
                        iPlayerValue = iPlayerValue + 10;
                    }
                    else
                    {
                        iDealerValue = iDealerValue + 10;
                    }
                }
                return String.valueOf(sCard);
            }
        }
        bUsed = false;        
        return "";
    }

    private ArrayList<String> slPlayerCards = new ArrayList<String>();
    private ArrayList<String> slDealerCards = new ArrayList<String>();

    String sHitStand = "";
    boolean bInputValid = false;

    public void main(String[] args)
    {   
        this.slValues.add("Ace");
        for(int i = 2; i < 11; i++)
        {
            this.slValues.add(Integer.toString(i));
        }
        this.slValues.add("Jack");
        this.slValues.add("Queen");
        this.slValues.add("King");

        for(int i = 0; i < 2; i++)
        {
            slPlayerCards.add(this.GenerateCard("Player"));
        }
        for(int i = 0; i < 2; i++)
        {
            slDealerCards.add(this.GenerateCard("Dealer"));
        }

        System.out.println("\n\nYour cards are: " + slPlayerCards.get(0) + ", " + slPlayerCards.get(1));
        System.out.println("The Dealers face up card is a: " + slDealerCards.get(0));
        System.out.println("Your total value is: " + iPlayerValue);
        
        // iPlayerValue = 21;
        if(iPlayerValue == 21)
        {
            System.out.println("\nYOU HAVE BLACKJACK!\n\nSo you win. \nWELL DONE!");
            System.exit(0);
        }

        while(bInputValid == false)
        {
            System.out.print("\nDo you want to hit (H) or Stand (S) \n  > ");
            Scanner myObj = new Scanner(System.in);
            sHitStand = "";
            sHitStand = myObj.nextLine().toUpperCase();

            if(sHitStand.equals("H"))
            {
                slPlayerCards.add(this.GenerateCard("Player"));
                System.out.println("Your new card is a: " + slPlayerCards.get(slPlayerCards.size() - 1));

                if(iPlayerValue > 21)
                {
                    System.out.println("So your total value is over 21...sorry you went bust and LOSE!");
                    System.exit(0);
                }

                System.out.println("Your new total value is: " + iPlayerValue);
            }
            else if(sHitStand.equals("S"))
            {
                while(iDealerValue < 17)
                {
                    slDealerCards.add(this.GenerateCard("Dealer"));
                }

                if(iDealerValue > 21 && iPlayerValue < 21)
                {
                    String sDealersCards = slDealerCards.toString();
                    System.out.println("The dealer went bust with a value of " + iDealerValue);
                    System.out.println("Their cards are; " + sDealersCards + "\n\nYOU WIN!");
                    System.exit(0);
                }
                else if(iPlayerValue == 21 && iDealerValue != 21)
                {
                    System.out.println("\nYou have 21 and the dealer doesn't \nYOU WIN!");
                    System.exit(0);
                }
                else if(iDealerValue == 21)
                {
                    System.out.println("\nUnfortunately the dealer has a -> " + slDealerCards.get(1) + " <- so they have BLACKJACK and you lose. \nBETTER LUCK NEXT TIME!");
                    System.exit(0);
                }
                else if(iPlayerValue == iDealerValue)
                {
                    System.out.println("\n\nYou both have the same value. \nIT'S A DRAW!");
                    System.exit(0);}
                else if(iPlayerValue > iDealerValue && iPlayerValue <= 21)
                {
                    System.out.println("\nThe dealer has a -> " + slDealerCards.get(1) + " so your value is higher.");
                    System.out.println("Their value is " + iDealerValue + "\nYOU WIN!");
                    System.exit(0);
                }
                else if(iPlayerValue < iDealerValue && iPlayerValue <= 21)
                {
                    System.out.println("\nThe dealer has a -> " + slDealerCards.get(1) + " <- so their value is higher.");
                    System.out.println("They have a value of " + iDealerValue + " vs your " + iPlayerValue + "\nYOU LOSE!");
                    System.exit(0);
                }
                else if(iPlayerValue > 21)
                {
                    System.out.println("Your total value is over 21...sorry you went bust and LOSE!");
                    System.exit(0);
                }
            }
            else
            {
                System.out.println("\n\n\nPlease input a valid choice.");
            }

            // myObj.close(); 
        }
    }
}