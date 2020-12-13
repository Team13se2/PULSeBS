import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext';
import {CanvasJSChart} from 'canvasjs-react-charts';
import moment from 'moment';

const MonthChartBookingManager = (props) => {

    let {lectures,getAllPastLectures} = props;
    // same as componentDidMount()
    useEffect(() => {
        let option = getLecturesPerMonth();
        setOption(option);
        console.log(lectures);
    },[lectures]);
    const [option,setOption] = React.useState("");

    function getYear(bookable){
        let onlyMonthPeopleAndBookable = lectures.map(l => {return {startTime: l.startTime, nrStudentsBooked: l.nrStudentsBooked, nrStudentsPresent: l.nrStudentsPresent,bookable: l.bookable }});
        let months =[];
        months.push({numMonth: 0,month: "jan",sum: 0});months.push({numMonth: 1,month: "feb",sum: 0});months.push({numMonth: 2,month: "mar",sum: 0});
        months.push({numMonth: 3,month: "apr",sum: 0});months.push({numMonth: 4,month: "may",sum: 0});months.push({numMonth: 5,month: "jun",sum: 0});
        months.push({numMonth: 6,month: "jul",sum: 0});months.push({numMonth: 7,month: "aug",sum: 0});months.push({numMonth: 8,month: "sept",sum: 0});
        months.push({numMonth: 9,month: "oct",sum: 0});months.push({numMonth: 10,month: "nov",sum: 0});months.push({numMonth: 11,month: "dec",sum: 0});
        onlyMonthPeopleAndBookable.forEach(element => {
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let dateTime = moment(element.startTime).format(format1);
            dateTime = moment(dateTime,"YYYY-MM-DD HH:mm:ss");
            //if(element.nrStudents == null){element.nrStudents = 0;} controllare se serve
            if(bookable){
                months[dateTime.month()].sum += element.nrStudentsBooked;
            }else{
                months[dateTime.month()].sum += element.nrStudentsBooked - element.nrStudentsPresent;
            }
        });
        return months;
    }

    function getAttendance(){
        let onlyMonthPeopleAndBookable = lectures.map(l => {return {startTime: l.startTime, nrStudentsPresent: l.nrStudentsPresent, bookable: l.bookable }});
        let months =[];
        months.push({numMonth: 0,month: "jan",sum: 0});months.push({numMonth: 1,month: "feb",sum: 0});months.push({numMonth: 2,month: "mar",sum: 0});
        months.push({numMonth: 3,month: "apr",sum: 0});months.push({numMonth: 4,month: "may",sum: 0});months.push({numMonth: 5,month: "jun",sum: 0});
        months.push({numMonth: 6,month: "jul",sum: 0});months.push({numMonth: 7,month: "aug",sum: 0});months.push({numMonth: 8,month: "sept",sum: 0});
        months.push({numMonth: 9,month: "oct",sum: 0});months.push({numMonth: 10,month: "nov",sum: 0});months.push({numMonth: 11,month: "dec",sum: 0});
        onlyMonthPeopleAndBookable.forEach(element => {
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let dateTime = moment(element.startTime).format(format1);
            dateTime = moment(dateTime,"YYYY-MM-DD HH:mm:ss");
            //if(element.nrStudents == null){element.nrStudents = 0;} controllare se serve
            months[dateTime.month()].sum += element.nrStudentsPresent;
        });
        return months;
    }

    function getLecturesPerMonth(){
        let monthsDelete = getYear(0);
        let months = getYear(1);
        let monthsAttendance = getAttendance();
        return {animationEnabled: true,	
            title:{
                text: "Number of Reservations"
            },
            axisY : {
                title: "Number of Reservations"
            },
            toolTip: {
                shared: true
            },
            data: [{
                type: "spline",
                name: "Number of deleted reservations",
                showInLegend: true,
                dataPoints: monthsDelete.map(l =>{return {y: l.sum,label: l.month}})
            },{
                type: "spline",
                name: "Number of reservations",
                showInLegend: true,
                dataPoints: months.map(l =>{return {y: l.sum,label: l.month}})
            },{
                type: "spline",
                name: "Number of attendances",
                showInLegend: true,
                dataPoints: monthsAttendance.map(l =>{return {y: l.sum,label: l.month}})
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

export default MonthChartBookingManager;
