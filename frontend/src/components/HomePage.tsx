import React from "react";
import { SearchBarComponent } from "./SearchBarComponent";
import styles from "../CSS/HomePage.module.css";

export const HomePage = () => {
  return (
    <div className={"home-header"}>
      <view className={styles.header}></view>
      <SearchBarComponent />
    </div>
  );
};
