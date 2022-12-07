import axios from "axios";
import React, { useEffect, useState } from "react";
import { Card } from "react-bootstrap";
import Grid from "../components/grid/Grid";
import { dashboard } from "../helpers/dashboardHelper";
import ReactEcharts from "echarts-for-react";

function Home() {

  const [lastTenJobs, setLastTenJobs] = useState();
  const [studentsPerState, setStudentsPerState] = useState();
  const [jobsPerLocation, setJobsPerLocation] = useState();
  const [lastTenAppliedJobs, setLastTenAppliedJobs] = useState();
  const getLastTenJobs = async () => {
    const response = await axios.get(dashboard.lastTenJobs.dataSrcRoute);
    console.log("last tenn", response);
    response.data.map(x => x.address = `${x.address.city},${x.address.state}`);
    setLastTenJobs(response.data);
  }
  const getLastTenAppliedJobs = async () => {
    const response = await axios.get(dashboard.lastTenAppliedJobs.dataSrcRoute);
    console.log(response.data);
    let jobApplications = response.data.map(x => {
      return {
        jobTitle: x.job.jobTitle,
        jobType: x.job.jobType,
        company: x.job.company.name,
        address: `${x.job.address.city},${x.job.address.state}`
      }
    });
    console.log("final data", jobApplications);
    setLastTenAppliedJobs(jobApplications);
  }

  const fetchNoOfJobsPerLocation = async () => {
    const response = await axios.get("/jobs/per-location");
    console.log("jobs per loc", response.data);
    const stPerState = {
      series: {
        data: response.data.map(x => { return { name: x.address, value: x.count } }),
        type: 'pie'
      }
    }
    setJobsPerLocation(stPerState);
  }

  const fetchNoOfStudentsPerState = async () => {
    const response = await axios.get("/students/perState");
    console.log("Students Per State:", response.data);
    const stPerState = {
      xAxis: {
        type: 'category',
        data: response.data.map(x => x.state)
      },
      yAxis: {
        type: 'value'
      },
      series: {
        data: response.data.map(x => x.count),
        type: 'bar'
      }
    }
    setStudentsPerState(stPerState);
  }


  useEffect(() => {

    fetchNoOfJobsPerLocation();
    fetchNoOfStudentsPerState();
  }, []);

  useEffect(() => {
    getLastTenJobs();
  }, []);
  useEffect(() => {
    getLastTenAppliedJobs();
  }, []);
  return (
    <div className="container">
      <div>
        <h1>Dashboard</h1>
      </div>
      <div className="row">
        <div className="col-md-6">
          <Card>
            <Card.Header>Students Per State</Card.Header>
            <Card.Body>
              {studentsPerState ? <ReactEcharts option={studentsPerState} /> : <></>}
            </Card.Body>
          </Card>
        </div>
        <div className="col-md-6">
          <Card>
            <Card.Header>Jobs Per Location</Card.Header>
            <Card.Body>
              {jobsPerLocation ? <ReactEcharts option={jobsPerLocation} /> : <></>}
            </Card.Body>
          </Card>
        </div>
      </div>
      <div className="row">
        <div className="col-md-6">
          <Card>
            <Card.Header>{dashboard.lastTenJobs.title}</Card.Header>
            <Card.Body>
              {/* <Card.Title>{dashboard.lastTenJobs.title}</Card.Title> */}
              <Grid list={lastTenJobs} />
            </Card.Body>
          </Card>
        </div>
        <div className="col-md-6">
          <Card>
            <Card.Header>{dashboard.lastTenAppliedJobs.title}</Card.Header>
            <Card.Body>
              <Grid list={lastTenAppliedJobs} />
            </Card.Body>
          </Card>
        </div>
      </div>

    </div>
  );
}

export default Home;
