import React, { useState } from "react";
import { Ring } from "react-awesome-spinners";

export default class Loader extends React.Component {
    render() {
      return (
          <Ring size="500" />
      );
    }
}
