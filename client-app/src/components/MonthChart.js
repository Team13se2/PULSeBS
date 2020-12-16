import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext';
import {CanvasJSChart} from 'canvasjs-react-charts';
import moment from 'moment';

const MonthChart = (props) => {

    let {lectures,getAllPastLectures} = props;
    // same as componentDidMount()
    useEffect(() => {
        //getAllPastLectures();
        let option = getLecturesPerMonth();
        setOption(option);
    }, [lectures]);
    const [option,setOption] = React.useState("");

    function getLecturesPerMonth(){
        let onlyMonthAndPeople = lectures.map(l => {return {startTime: l.startTime, nrStudentsPresent: l.nrStudentsPresent,nrStudentsBooked:l.nrStudentsBooked }});
        let months = [];
        let monthsBooked = [];
        months.push({numMonth: 0,month: "jan",sum: 0});months.push({numMonth: 1,month: "feb",sum: 0});months.push({numMonth: 2,month: "mar",sum: 0});
        months.push({numMonth: 3,month: "apr",sum: 0});months.push({numMonth: 4,month: "may",sum: 0});months.push({numMonth: 5,month: "jun",sum: 0});
        months.push({numMonth: 6,month: "jul",sum: 0});months.push({numMonth: 7,month: "aug",sum: 0});months.push({numMonth: 8,month: "sept",sum: 0});
        months.push({numMonth: 9,month: "oct",sum: 0});months.push({numMonth: 10,month: "nov",sum: 0});months.push({numMonth: 11,month: "dec",sum: 0});
        monthsBooked = months;
        onlyMonthAndPeople.forEach(element => {
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let dateTime = moment(element.startTime).format(format1);
            dateTime = moment(dateTime,"YYYY-MM-DD HH:mm:ss");
            //if(element.nrStudents == null){element.nrStudents = 0;} controllare se serve
            months[dateTime.month()].sum += element.nrStudentsPresent;
            monthsBooked[dateTime.month()].sum += element.nrStudentsBooked;
        });
        return {animationEnabled: true,	
            title:{
                text: "Number of Students per month"
            },
            axisY : {
                title: "Number of Students"
            },
            toolTip: {
                shared: true
            },
            data: [{
                type: "spline",
                name: "Number of present",
                showInLegend: true,
                
                dataPoints: months.map(l =>{return {y: l.sum,label: l.month}})
            },{
                type: "spline",
                name: "Number of booked",
                showInLegend: true,
                
                dataPoints: monthsBooked.map(l =>{return {y: l.sum,label: l.month}})
            }
            
            ]}
    }

    return(
        <AuthContext.Consumer>
          {(context) => (
              <>
              <CanvasJSChart options = {option}/>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default MonthChart;
