import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext';
import {CanvasJSChart} from 'canvasjs-react-charts';
import moment from 'moment';

const MonthChart = (props) => {

  /*  let options = {
        animationEnabled: true,	
        title:{
            text: "Number of New Customers"
        },
        axisY : {
            title: "Number of Customers"
        },
        toolTip: {
            shared: true
        },
        data: [{
            type: "spline",
            name: "2016",
            showInLegend: true,
            dataPoints: [
                { y: 155, label: "Jan" },
                { y: 150, label: "Feb" },
                { y: 152, label: "Mar" },
                { y: 148, label: "Apr" },
                { y: 142, label: "May" },
                { y: 150, label: "Jun" },
                { y: 146, label: "Jul" },
                { y: 149, label: "Aug" },
                { y: 153, label: "Sept" },
                { y: 158, label: "Oct" },
                { y: 154, label: "Nov" },
                { y: 150, label: "Dec" }
            ]
        },
        {
            type: "spline",
            name: "2017",
            showInLegend: true,
            dataPoints: [
                { y: 172, label: "Jan" },
                { y: 173, label: "Feb" },
                { y: 175, label: "Mar" },
                { y: 172, label: "Apr" },
                { y: 162, label: "May" },
                { y: 165, label: "Jun" },
                { y: 172, label: "Jul" },
                { y: 168, label: "Aug" },
                { y: 175, label: "Sept" },
                { y: 170, label: "Oct" },
                { y: 165, label: "Nov" },
                { y: 169, label: "Dec" }
            ]
        }]
}*/

    let {lectures,getAllPastLectures} = props;
    // same as componentDidMount()
    useEffect(() => {
        //getAllPastLectures();
        let option = getLecturesPerMonth();
        setOption(option);
    }, [lectures]);
    const [option,setOption] = React.useState("");

    function getLecturesPerMonth(){
        let onlyMonthAndPeople = lectures.map(l => {return {startTime: l.startTime, nrStudents: l.nrStudents }});
        let months = [];
        months.push({numMonth: 0,month: "jan",sum: 0});months.push({numMonth: 1,month: "feb",sum: 0});months.push({numMonth: 2,month: "mar",sum: 0});
        months.push({numMonth: 3,month: "apr",sum: 0});months.push({numMonth: 4,month: "may",sum: 0});months.push({numMonth: 5,month: "jun",sum: 0});
        months.push({numMonth: 6,month: "jul",sum: 0});months.push({numMonth: 7,month: "aug",sum: 0});months.push({numMonth: 8,month: "sept",sum: 0});
        months.push({numMonth: 9,month: "oct",sum: 0});months.push({numMonth: 10,month: "nov",sum: 0});months.push({numMonth: 11,month: "dec",sum: 0});
        onlyMonthAndPeople.forEach(element => {
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let dateTime = moment(element.startTime).format(format1);
            dateTime = moment(dateTime,"YYYY-MM-DD HH:mm:ss");
            //if(element.nrStudents == null){element.nrStudents = 0;} controllare se serve
            months[dateTime.month()].sum += element.nrStudents;
        });
        return {animationEnabled: true,	
            title:{
                text: "Number of Students CHANGE ME"
            },
            axisY : {
                title: "Number of Students"
            },
            toolTip: {
                shared: true
            },
            data: [{
                type: "spline",
                name: "Number of students",
                showInLegend: true,
                
                dataPoints: months.map(l =>{return {y: l.sum,label: l.month}})
            },
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
