import { ChangeEvent, FocusEvent, useState } from "react";

type useUsernameReturnType = {
  username: string;
  isValid: (username: string) => boolean;
  maxLength: number;
  invalidLengthMessage: string;
  handleChange: (e: ChangeEvent<HTMLInputElement>) => void;
  handleBlur: (e: FocusEvent<HTMLInputElement>) => void;
};

export const useUsername = (): useUsernameReturnType => {
  //
  const [username, setUsername] = useState<string>("");

  const isValid = (username: string): boolean => {
    return minLength <= username.length && username.length <= maxLength;
  };

  const maxLength: number = 30;
  const minLength: number = 5;

  const [invalidLengthMessage, setInvalidLengthMessage] = useState<string>("");

  const handleChange = (e: ChangeEvent<HTMLInputElement>): void => {
    setUsername(e.target.value);
  };

  const handleBlur = (e: FocusEvent<HTMLInputElement>): void => {
    //
    const value: string = e.target.value;

    if (!isValid(value)) {
      setInvalidLengthMessage(
        `ユーザ名は${minLength}文字以上${maxLength}文字以下で入力してください。`
      );
    } else {
      setInvalidLengthMessage("");
    }
  };

  return {
    username,
    isValid,
    invalidLengthMessage,
    maxLength,
    handleChange,
    handleBlur,
  };
};
