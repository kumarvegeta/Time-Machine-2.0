 /*
 * McKittrick Swindle
 * 
 * 09/29: t_dayOfWeek for 09/14/2018 returned incorrect day, investigate.
 * ""       Need to add leap year detection to target calc
 * ""       use switch to sum the days of previous months, for total traveled seconds calculation?
 * ""       Also need to throw all target calculations in a loop
 * ""       Inside target distance loop, need to update H:M:S before calculating total traveled seconds,
 *                  probably not any other numbers, as it would require recaculation of day of week, including another switch... meh...
 */

import java.util.*;

public class Entry
{
    public static void main(String[] args)
    {
        Scanner giv = new Scanner(System.in);
        Calendar cal = new GregorianCalendar();
        
        int dayOfWeek, dayNumber=0, monthNumber, hour, minute, second, year, yearTwo, centuries, t_year=0;
        double d_second = 0;
        String month = "";
        String day = "";
        char re = 'a', newTrip = 'n';
        
        dayNumber = cal.get(Calendar.DAY_OF_MONTH);
        monthNumber = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
        
        //System.out.println("The current timestamp is: "+monthNumber+" / "+dayNumber+" / "+year+ "  "+hour+" : "+minute+" : "+second);
        
        monthNumber++;
        
        switch(monthNumber)         //Assigns monthNumber (int) to month (string)
        {
            case 1: month = "Jan"; monthNumber = 13; break;         // Alligns these months for use with forumla to calculate day of the week. This is undone after dayOfWeek calc
            case 2: month = "Feb"; monthNumber = 14; break;        // """""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
            case 3: month = "Mar"; break;           // All remaining months are shifted by another +1 to allign with dayOfWeek formula requirement
            case 4: month = "Apr"; break;
            case 5: month = "May"; break;
            case 6: month = "Jun"; break;
            case 7: month = "Jul";  break;
            case 8: month = "Aug"; break;
            case 9: month = "Sep"; break;
            case 10: month = "Oct"; break;
            case 11: month = "Nov"; break;            
            case 12: month = "Dec"; break;
            default: System.out.println("Error at monthNumber out of 0-11 range");
        }
        
        /*System.out.println("Current timestamp v2: "+month+" "+dayNumber+" "+year+ "  "+hour+" : "+minute+" : "+second); 
        System.out.println("Calculating day of week...");   
        System.out.println("In century year:  " +yearTwo);                          //This comment block is from some verification of calcs of the next two parsed lines
        System.out.println("Century in XX:  " +centuries);                
        */
        yearTwo = year%100;
        centuries = (year - (year%100))/100;        
        dayOfWeek = (dayNumber + ((13*(monthNumber+1)/5)/1) + yearTwo + yearTwo/4 + centuries/4 + 5*centuries)%7;  
        
        switch(monthNumber)             //reset the Jan and Feb to default numbers after dayOfWeek is done.
        {
            case 13: monthNumber = 1;break;
            case 14: monthNumber = 2;break;
            default: break;
        }
        
        switch(dayOfWeek)
        {
            case 0: day = "Sat";break;
            case 1: day = "Sun";break;
            case 2: day = "Mon";break;
            case 3: day = "Tue";break;
            case 4: day = "Wed";break;
            case 5: day = "Thu";break;
            case 6: day = "Fri";break;
            default: System.out.println("Error, dayOfWeek calculation did not produce viable number! (0-6)");
            
        }
        
        //System.out.println("Day of the week: "+day);                          //Error checking again
        System.out.println("\nWelcome to the DeLorean! Current timestamp:\t"+day+" "+month+" "+dayNumber+" "+year+"  "+hour+":"+minute+":"+second);
                
        
        while(re == 'a')                                         //begin interactive calculations
        {
                int t_dayOfWeek, t_dayNumber=0, t_monthNumber, t_hour, t_minute, t_second, t_yearTwo, t_centuries;    // defining all the same variables, but TARGET time
                String t_month = "";
                String t_day = "";
                
                
                do
                {
                    System.out.print("Ready to calculate trip. Please input the destination time.\nYear: (XXXX)\t"); t_year = giv.nextInt();
                    System.out.print("Month: (XX)\t"); t_monthNumber = giv.nextInt();
                    System.out.print("Day: (XX)\t"); t_dayNumber = giv.nextInt();
                    System.out.print("HMS (HH MM SS)\t"); t_hour = giv.nextInt(); t_minute = giv.nextInt(); t_second = giv.nextInt();
                    
                                        if(t_hour >= 24)
                    {
                        System.out.println("The number you put in for hours is out of range! (0-23)");
                    }else if(t_minute >= 60){
                        System.out.println("The number you put in for minutes is out of range! (0-59)");
                    }else if(t_second >= 60){
                        System.out.println("The number you put in for seconds is out of range! (0-59)");
                    }else {
                        System.out.print("");
                    }
                    switch(t_monthNumber)
                    {
                        case 1: t_month = "Jan"; break;
                        case 2: t_month = "Feb"; break;
                        case 3: t_month = "Mar"; break;
                        case 4: t_month = "Apr"; break;
                        case 5: t_month = "May"; break;
                        case 6: t_month = "Jun"; break;
                        case 7: t_month = "Jul"; break;
                        case 8: t_month = "Aug"; break;
                        case 9: t_month = "Sep"; break;
                        case 10: t_month = "Oct"; break;
                        case 11: t_month = "Nov"; break;
                        case 12: t_month = "Dec"; break;
                        default: System.out.println("Error, you did not input a valid number for month. (1-12)");
                    }
                    if(t_dayNumber == 29 && t_monthNumber == 2 && !(t_year%4 == 0 && t_year%100 != 0 || t_year%400 == 0 && t_year%100 == 0))
                    {
                        System.out.println("You entered Feburary 29th on a non leap year. Please try again!");
                        t_monthNumber = 99;
                    }
                }while (t_monthNumber <= 0 || t_monthNumber > 12 || t_dayNumber <= 0 || t_dayNumber > 31 || t_hour <0 || t_hour >24 || t_minute <0 || t_minute > 59 || t_second <0 || t_second >59);
               
                switch(t_monthNumber)
                {
                    case 1: t_monthNumber = 13; break;
                    case 2: t_monthNumber = 14; break;
                    default: break;
                }
        
                t_yearTwo = t_year%100;
                t_centuries = (t_year - (t_year%100))/100;        
                t_dayOfWeek = (t_dayNumber + ((13*(t_monthNumber+1)/5)/1) + t_yearTwo + t_yearTwo/4 + t_centuries/4 + 5*t_centuries)%7;  
                
                switch(t_monthNumber)
                {
                    case 13: t_monthNumber = 1; break;
                    case 14: t_monthNumber = 2; break;
                    default: break;
                }
                        
                switch(t_dayOfWeek)
                {
                    case 0: t_day = "Sat";break;
                    case 1: t_day = "Sun";break;
                    case 2: t_day = "Mon";break;
                    case 3: t_day = "Tue";break;
                    case 4: t_day = "Wed";break;
                    case 5: t_day = "Thu";break;
                    case 6: t_day = "Fri";break;
                    default: System.out.println("Error, t_dayOfWeek calculation did not produce viable number! (0-6)");
                    
                }
                
                System.out.println("\nDestination Time: " +t_day+" "+t_month+" "+t_year+"   "+t_hour+":"+t_minute+":"+t_second);
                
                //Begin dT calculations
                
                int d_year, d_month=0;
                d_year =t_year-year;            // a positive number means we are going forward, negate means backwards
                if(d_year>0) //calculating difference in months, if we are moving forward
                { //if we cross New Years going forward, we need remaining months in origin year, and total months in destination year.
                    d_month = (12-monthNumber)+t_monthNumber;
                }else if(d_year<0) //if we are moving backwards, inverse is true
                {//if we cross New Years going backwards, we need months in origin year, and months remaining in target year.
                    d_month = (0-12+t_monthNumber)-monthNumber;
                }else if(d_year==0) //if this is true, we aren't leaving the year we're in, meaning it is indeterminant in our direction.
                {// either direction, we just need the difference in months. If we are going forward, this will be +, backwards it will be -
                    d_month = t_monthNumber-monthNumber;
                }
                //System.out.println("d_year: "+d_year+" d_month: " +d_month+" t_monthNumber: "+t_monthNumber+" monthNumber: "+monthNumber+ " dayNumber: "+dayNumber+" t_dayNumber: "+t_dayNumber);
                
                if(Math.abs(d_year) > 1) //enter if statement if there are whole years that must be traversed.
                {
                   while(d_year >1) //perform loop if the whole years to be traversed are in the future
                   {
                       if((year + d_year -1)%4 == 0 && (year+d_year -1)%100 != 0 || (year+d_year -1)%400 == 0 && (year+d_year -1)%100 == 0) //if year is a leap year, add 366 days worth of seconds
                       {
                           d_second += 366*86400;
                       } else 
                       {
                           d_second += 365*86400;
                        }
                        d_year--;         //after adding the seconds in furthest year from start, decrese by one year, recheck for leap year, and add correct seconds until no more whole years are to be traversed.
                   }
                    
                   while(d_year < -1) //perform loop if the whole years to be traversed are in the past
                   {
                        if((year + d_year +1)%4 == 0 && (year+d_year +1)%100 != 0 || (year+d_year +1)%400 == 0 && (year+d_year +1)%100 == 0) //leap year test
                        {
                            d_second += 366*86400;                            
                        } else
                        {
                            d_second += 365*86400;
                        }
                        d_year++;     //after adding seconds from furthest year from start (in the past) add one to the (negative) d_years, to find the days in 1 year closer to staring year, until no more whole years
                   }
                    
                }
                //System.out.println("Seconds traveled in whole years: " +d_second);
                while(Math.abs(d_month)>1)                  //while there are whole months to be summed
                {
                    if(d_month>1) //Add the seconds in whole months when we're going forward
                    {
                        switch((monthNumber + d_month-1) % 12)   // determines which month is being calculated, "--%12" allows us to find the month once we've crossed new years.
                        {
                            case 0: d_second += 31*86400; break;    //0 is actually december
                            case 1: d_second += 31*86400; break;    //January
                            case 2: d_second += 28*86400;
                                        if(monthNumber == 1 && d_month > 1 && (year%4 == 0 && year%100 != 0 || year%400 == 0 && year%100 == 0))
                                        {//if we are going forward, origin month is before Feburary, and origin year is a leap year,  
                                            d_second += 86400; //add leap day in origin year                                                                                    
                                        } if(t_monthNumber > 2 && d_year > 0 && (t_year%4 == 0 && t_year%100 != 0 || t_year%400 == 0 && t_year%100 == 0)) 
                                        {//if the target month is after Feburary in target year, we are passing New Years at some point, and the target year is a leap year, add a day.
                                            d_second += 86400; //add leap day in target year
                                        }break;
                            case 3: d_second += 31*86400; break;    //March
                            case 4: d_second += 30*86400; break;    //April
                            case 5: d_second += 31*86400; break;    //May
                            case 6: d_second += 30*86400; break;    //June
                            case 7: d_second += 31*86400; break;    //July
                            case 8: d_second += 31*86400; break;    //Aug
                            case 9: d_second += 30*86400; break;
                            case 10: d_second += 31*86400; break;
                            case 11: d_second += 30*86400; break;
                            default: System.out.print("Error at adding completed months' seconds. (monthNumber + d_month)%12 wasn't 0-11"); break;         
                        }
                        d_month--;
                    }else if(d_month<1) //all cases going backwards
                    {
                        switch(Math.abs((12 + monthNumber + d_month+1) % 12))   // determines which month is being calculated
                        {
                            case 0: d_second += 31*86400; break;    //0 is actually december
                            case 1: d_second += 31*86400; break;    //January
                            case 2: d_second += 28*86400;
                                        if(monthNumber > 2 && d_year<0 && (year%4 == 0 && year%100 != 0 || year%400 == 0 && year%100 == 0)) 
                                        {//if we are starting after feb, will cross New Year, and start in a leap year, add a day
                                            d_second += 86400; System.out.println("origin leap day added");
                                        } if(t_monthNumber == 1 && d_month <1 && (t_year%4 == 0 && t_year%100 != 0 || t_year%400 == 0 && t_year%100 == 0))      //the whole feburary must occur in the target year, therefore
                                        {//if we get to January of the target year, and had to calculate at least 1 whole month, we are calcing Feb, 
                                            d_second += 86400; System.out.println("target leap day added");
                                        }break;
                            case 3: d_second += 31*86400; break;    //March
                            case 4: d_second += 30*86400; break;    //April
                            case 5: d_second += 31*86400; break;    //May
                            case 6: d_second += 30*86400; break;    //June
                            case 7: d_second += 31*86400; break;    //July
                            case 8: d_second += 31*86400; break;    //Aug
                            case 9: d_second += 30*86400; break;
                            case 10: d_second += 31*86400; break;
                            case 11: d_second += 30*86400; break;
                            default: System.out.print("Error at adding completed months' seconds. (monthNumber + d_month)%12 wasn't 0-11"); break;
                        }      
                        d_month++;
                    }
                }
                // Whole years and whole months have been calculated by now. The only thing that should remain is whole days, then HMS.
                //System.out.println("Seconds traveled in whole years and months "+d_second);                
                if(d_month>0) //if we are going forward, add the remaining days of the current month, and days in target month
                {
                    switch(monthNumber) 
                    {
                        case 1: d_second += (31- dayNumber)*86400;break;
                        case 2: if(year%4 == 0 && year%100 != 0 || year%400 == 0 && year%100 == 0) //start date is in february and is a leap year
                                    {
                                        d_second += (29-dayNumber)*86400;break;
                                    } else
                                    {
                                        d_second += (28-dayNumber)*86400;break;
                                    }
                        case 3: d_second += (31-dayNumber)*86400;break;
                        case 4: d_second += (30-dayNumber)*86400;break;
                        case 5: d_second += (31-dayNumber)*86400;break;
                        case 6: d_second += (30-dayNumber)*86400;break;
                        case 7: d_second += (31-dayNumber)*86400;break;
                        case 8: d_second += (31-dayNumber)*86400;break;
                        case 9: d_second += (30-dayNumber)*86400;break;
                        case 10: d_second += (31-dayNumber)*86400;break;
                        case 11: d_second += (30-dayNumber)*86400;break;
                        case 12: d_second += (31-dayNumber)*86400;break;
                        default: break;
                    }
                    d_second += (t_dayNumber-1)*86400;
                }else if(d_month<0) //we are going backwards, so add remaining days in target month, with total days -1 in current month.
                {
                    switch(t_monthNumber)
                    {
                        case 1: d_second += (31- t_dayNumber)*86400;break;
                        case 2: if(year%4 == 0 && year%100 != 0 || year%400 == 0 && year%100 == 0) //start date is in february and is a leap year
                                    {
                                        d_second += (29-t_dayNumber)*86400;break;
                                    } else
                                    {
                                        d_second += (28-t_dayNumber)*86400;break;
                                    }
                        case 3: d_second += (31-t_dayNumber)*86400;break;
                        case 4: d_second += (30-t_dayNumber)*86400;break;
                        case 5: d_second += (31-t_dayNumber)*86400;break;
                        case 6: d_second += (30-t_dayNumber)*86400;break;
                        case 7: d_second += (31-t_dayNumber)*86400;break;
                        case 8: d_second += (31-t_dayNumber)*86400;break;
                        case 9: d_second += (30-t_dayNumber)*86400;break;
                        case 10: d_second += (31-t_dayNumber)*86400;break;
                        case 11: d_second += (30-t_dayNumber)*86400;break;
                        case 12: d_second += (31-t_dayNumber)*86400;break;
                        default: break;
                    }
                    d_second += (dayNumber-1)*86400;
                }
                //System.out.println("Seconds traveled in whole years, and months, and remaining days in months "+d_second+ " d_year: "+d_year);
                // Whole Year, Month, and Days have been calculated. The only thing that remains should be remaining HMS
                if(d_year>0) //if we are moving forward                
                {
                    d_second += (23-hour)*3600; //add remaining hours in the day
                    d_second += (59-minute)*60; //add remaining minutes
                    d_second += (60-second); //add remaining seconds
                    d_second += t_hour*3600; //add target day's hours
                    d_second += t_minute*60; //add target day's minutes
                    d_second += t_second; //add target day's seconds
                } else if (d_year<0) //if we are moving backwards
                {
                    d_second += hour*3600;
                    d_second += minute*60;
                    d_second += second;
                    d_second += (23-t_hour)*3600;
                    d_second += (59-t_minute)*60;
                    d_second += (60-t_second);
                }else if(d_year==0)//d_year is indeterminant on which direciton we are traveling, therefore, we start and end in the same year.
                {
                    if(d_month>0) //If we are moving forward
                    {
                        d_second += (23-hour)*3600; //add remaining hours in the day
                        d_second += (59-minute)*60; //add remaining minutes
                        d_second += (60-second); //add remaining seconds
                        d_second += t_hour*3600; //add target day's hours
                        d_second += t_minute*60; //add target day's minutes
                        d_second += t_second; //add target day's seconds
                    }else if(d_month<0) //if we are moving backwards
                    {
                        d_second += hour*3600;
                        d_second += minute*60;
                        d_second += second;
                        d_second += (23-t_hour)*3600;
                        d_second += (59-t_minute)*60;
                        d_second += (60-t_second);
                    }else if(d_year==0 && d_month==0) //if it is indeterminant by d_year and d_month, we must have started and finished in the same month
                    {
                        if(t_dayNumber>dayNumber) //if we are moving forward
                        {
                            d_second += (23-hour)*3600; //add remaining hours in the day
                            d_second += (59-minute)*60; //add remaining minutes
                            d_second += (60-second); //add remaining seconds
                            d_second += t_hour*3600; //add target day's hours
                            d_second += t_minute*60; //add target day's minutes
                            d_second += t_second; //add target day's seconds                            
                        }else if(t_dayNumber<dayNumber) //if we are moving backwards
                        {
                            d_second += hour*3600;
                            d_second += minute*60;
                            d_second += second;
                            d_second += (23-t_hour)*3600;
                            d_second += (59-t_minute)*60;
                            d_second += (60-t_second);                            
                        }else if(d_year==0 && d_month == 0 && t_dayNumber==dayNumber) //if again, it is indeterminant change in year, month, or day, it must be on the same day. (WTF??? JUST WAIT GOD DAMN IT))
                        {
                            if(t_hour>hour) //GOING FORWARD
                            {
                                d_second += (t_hour-hour+1)*3600;
                                d_second += t_minute*60;
                                d_second += t_second;
                                d_second += (59-minute)*60;
                                d_second += 60-second;
                            }else if(t_hour<hour)//GOING BACKWARDS
                            {
                                d_second += (hour-t_hour+1)*3600;
                                d_second += minute*60;
                                d_second += second;
                                d_second += (59-t_minute)*60;
                                d_second += 60-t_second;
                            }else if(d_year == 0 && d_month == 0 && t_dayNumber==dayNumber && t_hour == hour) //STILL MOTHERFUCKING INDETERMINANT
                            {
                                if(t_minute>minute) //going forward in the same hour
                                {
                                    d_second += (t_minute-minute+1)*60;
                                    d_second += t_second;
                                    d_second += 60-second;
                                }else if(t_minute<minute) //going backward in the same hour
                                {
                                    d_second += (minute-t_minute)*60;
                                    d_second += 60-t_second;
                                    d_second += second;
                                }else if(d_year == 0 && d_month == 0 && t_dayNumber==dayNumber && t_hour == hour && t_minute == minute) // This should be the last one 
                                {
                                    if(t_second>second) //going forward in the same minute.... WHY?
                                    {
                                        d_second += (t_second-second);
                                    }else if(t_second<second) //going backward in the same minute.... You're an idiot.
                                    {
                                        d_second += (second-t_second);
                                    }else if(d_year == 0 && d_month == 0 && t_dayNumber==dayNumber && t_hour == hour && t_minute == minute && t_second == second)
                                    {
                                        System.out.println("The only possible way you could have gotten this error is if you input the same destination time as current time, which shouldn't be easy....\n\nWell Done!");
                                    }                                    
                                }
                            }
                        }
                    }
                }
                
                System.out.println("The total amount of seconds to be traveled is  "+d_second);
                
                // Begin Plutonium Count
                double plut, plutAvail = 20000000;
                
                if (d_second > 283824000)
                {
                    plut = 0.001*(d_second-283824000) + (283824000-94608000)*.002 + 94608000*.005;
                }else if (d_second > 94608000)
                {
                    plut = .002*(d_second-94608000) + 94608000*.005;
                }else
                {
                    plut = .005*d_second;
                }
                
                plutAvail = plutAvail - plut;
                System.out.println("This trip will take " +plut/1000+ "g of Plutonium, and leave "+plutAvail/1000+"g available");
                if(plutAvail<0)
                {
                    System.out.println("WARNING: YOU DO NOT HAVE ENOUGH PLUTONIUM TO MAKE THIS TRIP!!!");                    
                }else if(plutAvail < 10000000)
                {
                    System.out.println("WARNING: YOU WILL NOT HAVE ENOUGH PLUTONIUM FOR A ROUND TRIP!!!");
                }
                
                
            System.out.println("Would you like to run (a)nother calculation, or (q)uit?");
            re = giv.next().charAt(0);
            if (re == 'a')
            {
                System.out.println("Is this a (n)ew trip, or a (c)ontinuation of the previous point?");    
                newTrip = giv.next().charAt(0);
            }
            
            if(newTrip == 'c')
            {                                       
                dayOfWeek = t_dayOfWeek;
                dayNumber = t_dayNumber;
                monthNumber = t_monthNumber;
                hour = t_hour;
                minute = t_minute;
                second = t_second;
                year = t_year;
                
                switch(monthNumber)         //Assigns monthNumber (int) to month (string)
                {
                    case 1: month = "Jan"; monthNumber = 13; break;         // Alligns these months for use with forumla to calculate day of the week. This is undone after dayOfWeek calc
                    case 2: month = "Feb"; monthNumber = 14; break;        // """""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
                    case 3: month = "Mar"; break;           // All remaining months are shifted by another +1 to allign with dayOfWeek formula requirement
                    case 4: month = "Apr"; break;
                    case 5: month = "May"; break;
                    case 6: month = "Jun"; break;
                    case 7: month = "Jul";  break;
                    case 8: month = "Aug"; break;
                    case 9: month = "Sep"; break;
                    case 10: month = "Oct"; break;
                    case 11: month = "Nov"; break;            
                    case 12: month = "Dec"; break;
                    default: System.out.println("Error at monthNumber out of 0-11 range");
                }
                
                /*System.out.println("Current timestamp v2: "+month+" "+dayNumber+" "+year+ "  "+hour+" : "+minute+" : "+second); 
                System.out.println("Calculating day of week...");   
                System.out.println("In century year:  " +yearTwo);                          //This comment block is from some verification of calcs of the next two parsed lines
                System.out.println("Century in XX:  " +centuries);                
                */
                yearTwo = year%100;
                centuries = (year - (year%100))/100;        
                dayOfWeek = (dayNumber + ((13*(monthNumber+1)/5)/1) + yearTwo + yearTwo/4 + centuries/4 + 5*centuries)%7;  
                
                switch(monthNumber)             //reset the Jan and Feb to default numbers after dayOfWeek is done.
                {
                    case 13: monthNumber = 1;break;
                    case 14: monthNumber = 2;break;
                    default: break;
                }
                
                switch(dayOfWeek)
                {
                    case 0: day = "Sat";break;
                    case 1: day = "Sun";break;
                    case 2: day = "Mon";break;
                    case 3: day = "Tue";break;
                    case 4: day = "Wed";break;
                    case 5: day = "Thu";break;
                    case 6: day = "Fri";break;
                    default: System.out.println("Error, dayOfWeek calculation did not produce viable number! (0-6)");
                    
                }
                
                //System.out.println("Day of the week: "+day);                          //Error checking again
                System.out.println("\nAssuming timestamp:\t"+day+" "+month+" "+dayNumber+" "+year+"  "+hour+":"+minute+":"+second);
            } else
            {
                d_second = 0;
                System.out.println("\nAssuming timestamp:\t"+day+" "+month+" "+dayNumber+" "+year+"  "+hour+":"+minute+":"+second);
            }
         }
    }
    
}
