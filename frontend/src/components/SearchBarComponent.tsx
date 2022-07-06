import React from "react";
import { Input } from "@douyinfe/semi-ui";
import { IconSearch } from "@douyinfe/semi-icons";

export const SearchBarComponent = () => {
  return <Input prefix={<IconSearch />} placeholder={"Search..."} />;
};
